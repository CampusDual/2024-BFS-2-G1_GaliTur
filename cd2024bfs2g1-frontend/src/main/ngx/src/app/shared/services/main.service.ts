import {HttpHeaders} from '@angular/common/http';
import {Injectable, Injector} from '@angular/core';

import {OntimizeEEService, AppConfig, AuthService} from 'ontimize-web-ngx';
import {Observable} from 'rxjs';

@Injectable()
export class MainService extends OntimizeEEService {
  private appConfig: AppConfig;

  constructor(protected injector: Injector) {
    super(injector);
    this.appConfig = injector.get(AppConfig);
  }

  public buildHeaders(): HttpHeaders {
    let headers = new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer ' + this.authService.getSessionInfo().id
    });
    return headers;
  }

  public getUserInfo(): Observable<any> {
    const options = {headers: this.buildHeaders()};
    const requestBody = {};
    return this.httpClient.post(
      this._appConfig.apiEndpoint + '/users/loginUser/search',
      requestBody,
      options);
  }

  public getUserInfoByLoginAndId(login: string, email: string): Observable<any> {
    const options = {headers: this.buildHeaders()};
    const requestBody = {
      filter: {
        "@basic_expression": {
          lop: {
            lop: "usr_login",
            op: "=",
            rop: login
          },
          op: "OR",
          rop: {
            lop: "usr_email",
            op: "=",
            rop: email
          }
        }
      },
      columns: ["usr_id","usr_login", "usr_email"]
    }
    return this.httpClient.post(
      this._appConfig.apiEndpoint + '/users/user/search',
      requestBody,
      options
    )
  }
}
