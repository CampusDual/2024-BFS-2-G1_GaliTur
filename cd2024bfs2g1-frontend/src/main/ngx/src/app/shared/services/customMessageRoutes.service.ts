import { Injectable } from "@angular/core";
import { OFormMessageService } from "ontimize-web-ngx";

@Injectable()
export class CustomMessageServiceRoutes extends OFormMessageService {



  getDiscardChangesConfirmationMessage(): string {
    return 'MESSAGES_FORM_CHANGES_WILL_BE_LOST_ROUTE';
  }

  getInsertSuccessMessage(): any {
      return 'MESSAGES_INSERTED_ROUTE';
  }
}
