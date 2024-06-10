import { Injectable } from "@angular/core";
import { OFormMessageService } from "ontimize-web-ngx";

@Injectable()
export class CustomMessageServiceLandmarks extends OFormMessageService {

  getDeleteSuccessMessage(){
    return 'MESSAGES_LANDMARK_DELETE';
  }

  getDiscardChangesConfirmationMessage(): string {
    return 'MESSAGES_FORM_CHANGES_WILL_BE_LOST_LANDMARK';
  }

  getInsertSuccessMessage(): any {
      return 'MESSAGES_INSERTED_LANDMARK';
  }


}
