import { Injectable, Injector } from "@angular/core";
import { Codes, Observable, OntimizeEEPermissionsService, Util } from "ontimize-web-ngx";
import { share } from "rxjs";

@Injectable()
export class CustomPermissionsService extends OntimizeEEPermissionsService {

  constructor(protected injector: Injector) {
    super(injector);
  }

  loadPermissions(): Observable<any> {
    const isLoggedIn = this.authService.isLoggedIn();
    if (isLoggedIn) {
      return super.loadPermissions();
    } else {
      return this.loadPublicPermissions();
    }
  }

  loadPublicPermissions(): Observable<any> {
    const url = './assets/permissions/public-permission.json';
    const self = this;
    const dataObservable: Observable<any> = new Observable(_innerObserver => {
      self.httpClient.get(url).subscribe((res: any) => {
        let permissions = {};
        if ((res.code === Codes.ONTIMIZE_SUCCESSFUL_CODE) && Util.isDefined(res.data)) {
          const response = res.data;
          try {
            permissions = response;
          } catch (e) {
            console.warn('[CustomPermissionsService: permissions parsing failed]');
          }
        }
        _innerObserver.next(permissions);

      }, error => {
        _innerObserver.error(error);
      }, () => _innerObserver.complete());
    });
    return dataObservable.pipe(share());
  }
}