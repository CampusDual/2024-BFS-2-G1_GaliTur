import { Injectable } from "@angular/core";
import { OFormMessageService } from "ontimize-web-ngx";

@Injectable()
export class customMessageManagePack extends OFormMessageService {

  getDiscardChangesConfirmationMessage(): string {
    return 'MESSAGES_FORM_CHANGES_WILL_BE_LOST_MANAGEPACK';
  }

  getInsertSuccessMessage(): any {
    return 'MESSAGES_INSERTED_MANAGEPACK';
  }
  getInsertErrorMessage(): any{
    return 'MESSAGES_INSERTED_ERROR_MANAGEPACK';
  }
  getUpdateErrorMessage(): any{
    return 'MESSAGES_UPDATE_ERROR_MANAGEPACK';
  }
  getDeleteSuccessMessage(): any{
    return 'MESSAGES_DELETE_MANAGEPACK';
  }
  getUpdateSuccessMessage(): any{
    return 'MESSAGES_UPDATE_SUCCESS_MANAGEPACK';
  }
  getDeleteErrorMessage(): any {
    return 'MESSAGES_DELETE_ERROR_MANAGEPACK';
  }
  getDeleteConfirmationMessage(): any {
    return 'MESSAGES_DELETE_CONFIRMATION_MANAGEPACK';
  }
 

}