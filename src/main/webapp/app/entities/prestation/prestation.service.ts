import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPrestation } from 'app/shared/model/prestation.model';

type EntityResponseType = HttpResponse<IPrestation>;
type EntityArrayResponseType = HttpResponse<IPrestation[]>;

@Injectable({ providedIn: 'root' })
export class PrestationService {
  public resourceUrl = SERVER_API_URL + 'api/prestations';

  constructor(protected http: HttpClient) {}

  create(prestation: IPrestation): Observable<EntityResponseType> {
    return this.http.post<IPrestation>(this.resourceUrl, prestation, { observe: 'response' });
  }

  update(prestation: IPrestation): Observable<EntityResponseType> {
    return this.http.put<IPrestation>(this.resourceUrl, prestation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrestation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrestation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
