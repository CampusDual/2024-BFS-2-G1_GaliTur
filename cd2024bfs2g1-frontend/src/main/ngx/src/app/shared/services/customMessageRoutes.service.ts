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
  getInsertErrorMessage(): string{
      return 'MESSAGES_ROUTE_INSERT_ERROR';
  }
  getUpdateSuccessMessage(): string{
      return 'MESSAGES_ROUTE_UPDATE_SUCCESS';
  }
  getUpdateErrorMessage(): string{
     return 'MESSAGES_ROUTE_UPDATE_ERROR';
  }
  getDeleteSuccessMessage(): string{
     return 'MESSAGES_ROUTE_DELETE_SUCCESS';
  }
  getDeleteErrorMessage(): string{
      return 'MESSAGES_ROUTE_DELETE_ERROR';
  }


}
