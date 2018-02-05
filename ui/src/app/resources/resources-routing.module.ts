import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ResourceListComponent} from './resource-list/resource-list.component';
import {ResourceDetailComponent} from './resource-detail/resource-detail.component';
import {ResourcesComponent} from './resources.component';

const resourcesRoutes: Routes = [
  {
    path: '',
    component: ResourcesComponent,
    children: [
      {path: '', component: ResourceListComponent},
      {path: ':id', component: ResourceDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(resourcesRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class ResourceRoutingModule {
}
