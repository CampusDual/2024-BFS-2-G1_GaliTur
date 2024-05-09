import { Component } from '@angular/core';
import { Expression, FilterExpressionUtils, Util } from 'ontimize-web-ngx';

@Component({
  selector: 'app-view-all-landmark',
  templateUrl: './view-all-landmark.component.html',
  styleUrls: ['./view-all-landmark.component.css']
})
export class ViewAllLandmarkComponent {

  constructor() { }

  ngOnInit() {
  }
  createFilter(values: Array<{ attr; value }>): Expression {
    // Prepare simple expressions from the filter components values
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (Util.isDefined(fil.value)) {
        if (
          fil.attr === "name" ||
          fil.attr === "description" ||
          fil.attr === "opening_time" ||
          fil.attr === "closing_time" ||
          fil.attr === "coordinates" 
        ) {
          filters.push(
            FilterExpressionUtils.buildExpressionLike(
              "name" + fil.attr,
              fil.value
            )
          );
        }

      }
    });

    // Build complex expression
    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) =>
        FilterExpressionUtils.buildComplexExpression(
          exp1,
          exp2,
          FilterExpressionUtils.OP_AND
        )
      );
    } else {
      return null;
    }
  }

}
