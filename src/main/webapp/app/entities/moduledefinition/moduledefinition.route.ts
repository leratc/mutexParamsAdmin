import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Moduledefinition } from 'app/shared/model/moduledefinition.model';
import { ModuledefinitionService } from './moduledefinition.service';
import { ModuledefinitionComponent } from './moduledefinition.component';
import { ModuledefinitionDetailComponent } from './moduledefinition-detail.component';
import { ModuledefinitionUpdateComponent } from './moduledefinition-update.component';
import { ModuledefinitionDeletePopupComponent } from './moduledefinition-delete-dialog.component';
import { IModuledefinition } from 'app/shared/model/moduledefinition.model';

@Injectable({ providedIn: 'root' })
export class ModuledefinitionResolve implements Resolve<IModuledefinition> {
  constructor(private service: ModuledefinitionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IModuledefinition> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Moduledefinition>) => response.ok),
        map((moduledefinition: HttpResponse<Moduledefinition>) => moduledefinition.body)
      );
    }
    return of(new Moduledefinition());
  }
}

export const moduledefinitionRoute: Routes = [
  {
    path: '',
    component: ModuledefinitionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Moduledefinitions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModuledefinitionDetailComponent,
    resolve: {
      moduledefinition: ModuledefinitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Moduledefinitions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModuledefinitionUpdateComponent,
    resolve: {
      moduledefinition: ModuledefinitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Moduledefinitions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModuledefinitionUpdateComponent,
    resolve: {
      moduledefinition: ModuledefinitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Moduledefinitions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const moduledefinitionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ModuledefinitionDeletePopupComponent,
    resolve: {
      moduledefinition: ModuledefinitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Moduledefinitions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
