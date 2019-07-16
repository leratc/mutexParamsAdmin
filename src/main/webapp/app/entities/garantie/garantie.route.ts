import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Garantie } from 'app/shared/model/garantie.model';
import { GarantieService } from './garantie.service';
import { GarantieComponent } from './garantie.component';
import { GarantieDetailComponent } from './garantie-detail.component';
import { GarantieUpdateComponent } from './garantie-update.component';
import { GarantieDeletePopupComponent } from './garantie-delete-dialog.component';
import { IGarantie } from 'app/shared/model/garantie.model';

@Injectable({ providedIn: 'root' })
export class GarantieResolve implements Resolve<IGarantie> {
  constructor(private service: GarantieService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGarantie> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Garantie>) => response.ok),
        map((garantie: HttpResponse<Garantie>) => garantie.body)
      );
    }
    return of(new Garantie());
  }
}

export const garantieRoute: Routes = [
  {
    path: '',
    component: GarantieComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Garanties'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GarantieDetailComponent,
    resolve: {
      garantie: GarantieResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Garanties'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GarantieUpdateComponent,
    resolve: {
      garantie: GarantieResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Garanties'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GarantieUpdateComponent,
    resolve: {
      garantie: GarantieResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Garanties'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const garantiePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GarantieDeletePopupComponent,
    resolve: {
      garantie: GarantieResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Garanties'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
