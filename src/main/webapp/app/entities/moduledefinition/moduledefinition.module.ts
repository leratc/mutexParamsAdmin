import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MutexParamsAdminSharedModule } from 'app/shared';
import {
  ModuledefinitionComponent,
  ModuledefinitionDetailComponent,
  ModuledefinitionUpdateComponent,
  ModuledefinitionDeletePopupComponent,
  ModuledefinitionDeleteDialogComponent,
  moduledefinitionRoute,
  moduledefinitionPopupRoute
} from './';

const ENTITY_STATES = [...moduledefinitionRoute, ...moduledefinitionPopupRoute];

@NgModule({
  imports: [MutexParamsAdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ModuledefinitionComponent,
    ModuledefinitionDetailComponent,
    ModuledefinitionUpdateComponent,
    ModuledefinitionDeleteDialogComponent,
    ModuledefinitionDeletePopupComponent
  ],
  entryComponents: [
    ModuledefinitionComponent,
    ModuledefinitionUpdateComponent,
    ModuledefinitionDeleteDialogComponent,
    ModuledefinitionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MutexParamsAdminModuledefinitionModule {}
