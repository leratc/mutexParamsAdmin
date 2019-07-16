import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IModuledefinition } from 'app/shared/model/moduledefinition.model';

type EntityResponseType = HttpResponse<IModuledefinition>;
type EntityArrayResponseType = HttpResponse<IModuledefinition[]>;

@Injectable({ providedIn: 'root' })
export class ModuledefinitionService {
  public resourceUrl = SERVER_API_URL + 'api/moduledefinitions';

  constructor(protected http: HttpClient) {}

  create(moduledefinition: IModuledefinition): Observable<EntityResponseType> {
    return this.http.post<IModuledefinition>(this.resourceUrl, moduledefinition, { observe: 'response' });
  }

  update(moduledefinition: IModuledefinition): Observable<EntityResponseType> {
    return this.http.put<IModuledefinition>(this.resourceUrl, moduledefinition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModuledefinition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModuledefinition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
