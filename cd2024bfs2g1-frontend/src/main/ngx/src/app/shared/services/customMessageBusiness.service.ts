import { Injectable } from "@angular/core";
import { OFormMessageService } from "ontimize-web-ngx";

@Injectable()
export class CustomMessageBusinessService extends OFormMessageService {



  getInsertErrorMessage(): string {
    return 'Message-error';
  }

  getInsertSuccessMessage(): any {
      return 'Message-insert';
  }
}
