import { Injectable } from "@angular/core";
import { OFormMessageService } from "ontimize-web-ngx";

@Injectable()
export class CustomMessageServiceRegister extends OFormMessageService {
  getInsertSuccessMessage(): any {
      return 'MESSAGES_INSERTED_REGISTER';
  }
}
