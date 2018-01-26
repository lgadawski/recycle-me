import { Injectable } from '@angular/core';
import { Resource } from './resource';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';


@Injectable()
export class ResourcesService {

private resourcesUrl = 'api/resources';

constructor(
  private http: HttpClient
) { }

  public get(): Observable<Resource[]>  {
    return this.http.get<Resource[]>(this.resourcesUrl)
      .pipe(
        catchError(this.handleError('get', []))
      );
  }

  public post(body) {
    return this.http
      .post(this.resourcesUrl, JSON.stringify(body), {
        headers: new HttpHeaders().set('Content-Type', 'application/form-data'),
      })
      .subscribe();
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
