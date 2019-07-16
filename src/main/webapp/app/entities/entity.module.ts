import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'moduledefinition',
        loadChildren: './moduledefinition/moduledefinition.module#MutexParamsAdminModuledefinitionModule'
      },
      {
        path: 'produit',
        loadChildren: './produit/produit.module#MutexParamsAdminProduitModule'
      },
      {
        path: 'garantie',
        loadChildren: './garantie/garantie.module#MutexParamsAdminGarantieModule'
      },
      {
        path: 'prestation',
        loadChildren: './prestation/prestation.module#MutexParamsAdminPrestationModule'
      },
      {
        path: 'rubrique',
        loadChildren: './rubrique/rubrique.module#MutexParamsAdminRubriqueModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MutexParamsAdminEntityModule {}
