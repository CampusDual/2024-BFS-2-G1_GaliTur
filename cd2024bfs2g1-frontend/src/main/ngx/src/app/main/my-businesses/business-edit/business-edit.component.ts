import { Component, Injector, ViewChild } from "@angular/core";
import {
  AbstractControl,
  FormControl,
  ValidationErrors,
  ValidatorFn
} from "@angular/forms";
import { DomSanitizer } from "@angular/platform-browser";
import { ActivatedRoute, Router } from "@angular/router";
import {
  OCheckboxComponent,
  OComboComponent,
  OCurrencyInputComponent,
  OFormComponent,
  OTranslateService,
  OntimizeService
} from "ontimize-web-ngx";

@Component({
  selector: "app-business-edit",
  templateUrl: "./business-edit.component.html",
  styleUrls: ["./business-edit.component.css"],
})
export class BusinessEditComponent {
  selectedOption: number;
  validatorsDniCif: ValidatorFn[] = [];
  blankValidator: ValidatorFn[] = [];
  protected agencyGuideService: OntimizeService;
  public respuestaLanguage = [];
  public respuestaZone = [];
  public respuestaCity = [];

  esLanguageInicial = true;
  esZoneInicial = true;
  esCityInicial = true;

  public switchDestinationState: boolean = false;
  @ViewChild("switchDestination", { static: false })
  switchDestination: OCheckboxComponent;
  @ViewChild("currency1", { static: false }) currency1: OCurrencyInputComponent;

  public switchDestinationState2: boolean = false;
  @ViewChild("switchDestination2", { static: false })
  switchDestination2: OCheckboxComponent;
  @ViewChild("currency2", { static: false }) currency2: OCurrencyInputComponent;

  public switchDestinationState3: boolean = false;
  @ViewChild("switchDestination3", { static: false })
  switchDestination3: OCheckboxComponent;
  @ViewChild("currency3", { static: false }) currency3: OCurrencyInputComponent;
  @ViewChild("oFormAgencyNew") form: OFormComponent;
  @ViewChild("comboLanguages") comboLanguages: OComboComponent;
  @ViewChild("comboZone") comboZone: OComboComponent;
  @ViewChild("comboCity") comboCity: OComboComponent;

  constructor(
    public injector: Injector,
    private router: Router,
    protected sanitizer: DomSanitizer,
    private activeRoute: ActivatedRoute
  ) {
    this.validatorsDniCif.push(this.dniAndCifValidator);
    this.blankValidator.push(this.blanksValidator);
    this.blankValidator.push(this.lengthInvalid),
      (this.agencyGuideService = this.injector.get(OntimizeService));
  }

  protected configureAGService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf =
      this.agencyGuideService.getDefaultServiceConfiguration("agencyGuides");
    this.agencyGuideService.configureService(conf);
  }

  finish() {
    this.router.navigate([
      "main/business-merchant",
      this.activeRoute.snapshot.params["bsn_id"],
    ]);
  }

  reloadPage(): void {
    window.location.reload();
  }

  getSwitchValue() {
    this.switchDestinationState = this.switchDestination.getValue();
    this.currency1.setValue(null);
  }

  getSwitchValue2() {
    this.switchDestinationState2 = this.switchDestination2.getValue();
    this.currency2.setValue(null);
  }

  getSwitchValue3() {
    this.switchDestinationState3 = this.switchDestination3.getValue();
    this.currency3.setValue(null);
  }

  lengthInvalid = (control: FormControl) => {
    const isTooLong = (control.value || "").length > 500;
    const isValid = !isTooLong;
    return isValid ? null : { lengthInvalid: true };
  };

  dniAndCifValidator(control: AbstractControl): ValidationErrors | null {
    try {
      const cifRegex = /^([A-Z])(\d{8})$/;
      const dniRegex = /^(\d{8}[A-Za-z])$/;
      const inputValue = control.value.trim();

      if (cifRegex.test(inputValue) || dniRegex.test(inputValue)) {
        if (dniRegex.test(inputValue)) {
          const dniNumber = Number(inputValue.substring(0, 8));
          const dniLetter = inputValue.substring(8).toUpperCase();
          const letterIndex = dniNumber % 23;
          const letters = "TRWAGMYFPDXBNJZSQVHLCKE";
          const expectedLetter = letters.charAt(letterIndex);

          if (dniLetter === expectedLetter) {
            return null;
          } else {
            return { invalidDniLetter: true };
          }
        } else {
          return null;
        }
      } else {
        return { dniOrCifFormatError: true };
      }
    } catch (e) {}
  }

  blanksValidator(control: AbstractControl): ValidationErrors | null {
    try {
      const blank = /^[a-zA-Z].*/;
      const inputValue = control.value.trim();

      if (blank.test(inputValue)) {
        return null;
      } else {
        return { blankInvalid: true };
      }
    } catch (e) {}
  }

  getLanguageData() {
    if (this.esLanguageInicial) {
      this.configureAGService();

      const filter = {
        bsn_id: parseInt(this.activeRoute.snapshot.params["bsn_id"]),
      };
      const columns = ["bsn_id"];
      this.agencyGuideService
        .query(filter, columns, "agencyGuideEdit")
        .subscribe((resp) => {
          if (resp.code === 0) {
            // resp.data contains the data retrieved from the server
            const data = resp.data[0];
            this.respuestaLanguage = data["comboLanguages"];
            this.comboLanguages.setSelectedItems(this.respuestaLanguage);
            console.log(this.comboLanguages.getSelectedItems());

            this.esLanguageInicial = false;
          } else {
            alert("Impossible to query data!");
          }
        });
    }
  }

  getZoneData() {
    if (this.esZoneInicial) {
      this.configureAGService();

      const filter = {
        bsn_id: parseInt(this.activeRoute.snapshot.params["bsn_id"]),
      };
      const columns = ["bsn_id"];
      this.agencyGuideService
        .query(filter, columns, "agencyGuideEditProvince")
        .subscribe((resp) => {
          if (resp.code === 0) {
            // resp.data contains the data retrieved from the server
            const data = resp.data[0];
            this.respuestaZone = data["comboZone"];
            this.comboZone.setSelectedItems(this.respuestaZone);
            console.log(this.comboZone.getSelectedItems());

            this.esZoneInicial = false;

            this.getCityData();
          } else {
            alert("Impossible to query data!");
          }
        });
    }else{
      this.comboCity.clearValue();
    }
  }

  getCityData() {
    if (this.esCityInicial) {
      this.configureAGService();

      const filter = {
        bsn_id: parseInt(this.activeRoute.snapshot.params["bsn_id"]),
      };
      const columns = ["bsn_id"];
      this.agencyGuideService
        .query(filter, columns, "agencyGuideEditCity")
        .subscribe((resp) => {
          if (resp.code === 0) {
            // resp.data contains the data retrieved from the server
            const data = resp.data[0];
            this.respuestaCity = data["comboCity"];
            this.comboCity.setSelectedItems(this.respuestaCity);
            console.log(this.comboCity.getSelectedItems());

            this.esCityInicial = false;
          } else {
            alert("Impossible to query data!");
          }
        });
    }
  }
}
