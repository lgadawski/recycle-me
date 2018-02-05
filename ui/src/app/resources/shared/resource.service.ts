import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {AppConfig} from '../../config/app.config';

import {Resource} from './resource.model';
import {Observable} from 'rxjs/Rx';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class ResourceService {
  private headers: HttpHeaders;
  private resourcesUrl: string;
  private translations: any;

  private handleError(error: any) {
    if (error instanceof Response) {
      return Observable.throw(error.json()['error'] || 'backend server error');
    }
    return Observable.throw(error || 'backend server error');
  }

  constructor(private http: HttpClient,
              private translateService: TranslateService,
              private snackBar: MatSnackBar) {
    this.resourcesUrl = AppConfig.endpoints.resources;
    this.headers = new HttpHeaders({'Content-Type': 'application/json'});

    this.translateService.get(['resourceCreated', 'saved', 'resourceLikeMaximum', 'resourceRemoved'], {
      'value': AppConfig.votesLimit
    }).subscribe((texts) => {
      this.translations = texts;
    });
  }

  getAllResources(): Observable<Resource[]> {
    return this.http.get(this.resourcesUrl)
      .map(response => {
        return response;
      })
      .catch(error => this.handleError(error));
  }

  getResourceById(resourceId: string): Observable<Resource> {
    return this.http.get(this.resourcesUrl + '/' + resourceId)
      .map(response => {
        return response;
      })
      .catch(error => this.handleError(error));
  }

  createResource(resource: any): Observable<Resource> {
    return this.http
      .post(this.resourcesUrl, JSON.stringify({
        id: -1,
        name: resource.name,
        symbol: resource.symbol
      }), {headers: this.headers})
      .map(response => {
        this.showSnackBar('resourceCreated');
        return response;
      })
      .catch(error => this.handleError(error));
  }

  deleteResourceById(id: any): Observable<Array<Resource>> {
    const url = `${this.resourcesUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .map((response) => {
        this.showSnackBar('resourceRemoved');
        return response;
      })
      .catch(error => this.handleError(error));
  }

  showSnackBar(name): void {
    const config: any = new MatSnackBarConfig();
    config.duration = AppConfig.snackBarDuration;
    this.snackBar.open(this.translations[name], 'OK', config);
  }
}
