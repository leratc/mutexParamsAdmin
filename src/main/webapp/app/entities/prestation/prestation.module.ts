import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MutexParamsAdminSharedModule } from 'app/shared';
import {
  PrestationComponent,
  PrestationDetailComponent,
  PrestationUpdateComponent,
  PrestationDeletePopupComponent,
  PrestationDeleteDialogComponent,
  prestationRoute,
  prestationPopupRoute
} from './';

const ENTITY_STATES = [...prestationRoute, ...prestationPopupRoute];

@NgModule({
  imports: [MutexParamsAdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PrestationComponent,
    PrestationDetailComponent,
    PrestationUpdateComponent,
    PrestationDeleteDialogComponent,
    PrestationDeletePopupComponent
  ],
  entryComponents: [PrestationComponent, PrestationUpdateComponent, PrestationDeleteDialogComponent, PrestationDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MutexParamsAdminPrestationModule {}
