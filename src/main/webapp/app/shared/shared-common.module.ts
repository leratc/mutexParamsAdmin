import { NgModule } from '@angular/core';

import { MutexParamsAdminSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [MutexParamsAdminSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [MutexParamsAdminSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class MutexParamsAdminSharedCommonModule {}
