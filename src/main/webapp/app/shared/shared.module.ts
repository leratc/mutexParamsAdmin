import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MutexParamsAdminSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [MutexParamsAdminSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [MutexParamsAdminSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MutexParamsAdminSharedModule {
  static forRoot() {
    return {
      ngModule: MutexParamsAdminSharedModule
    };
  }
}
