import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Prestation } from 'app/shared/model/prestation.model';
import { PrestationService } from './prestation.service';
import { PrestationComponent } from './prestation.component';
import { PrestationDetailComponent } from './prestation-detail.component';
import { PrestationUpdateComponent } from './prestation-update.component';
import { PrestationDeletePopupComponent } from './prestation-delete-dialog.component';
import { IPrestation } from 'app/shared/model/prestation.model';

@Injectable({ providedIn: 'root' })
export class PrestationResolve implements Resolve<IPrestation> {
  constructor(private service: PrestationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPrestation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Prestation>) => response.ok),
        map((prestation: HttpResponse<Prestation>) => prestation.body)
      );
    }
    return of(new Prestation());
  }
}

export const prestationRoute: Routes = [
  {
    path: '',
    component: PrestationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Prestations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PrestationDetailComponent,
    resolve: {
      prestation: PrestationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Prestations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PrestationUpdateComponent,
    resolve: {
      prestation: PrestationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Prestations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PrestationUpdateComponent,
    resolve: {
      prestation: PrestationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Prestations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const prestationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PrestationDeletePopupComponent,
    resolve: {
      prestation: PrestationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Prestations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
