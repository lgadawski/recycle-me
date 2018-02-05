import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ResourceRoutingModule} from './resources-routing.module';
import {SharedModule} from '../shared/modules/shared.module';

import {ResourceListComponent, RemoveResourceDialogComponent} from './resource-list/resource-list.component';
import {ResourceService} from './shared/resource.service';
import {ResourceDetailComponent} from './resource-detail/resource-detail.component';
import {ResourcesComponent} from './resources.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    ResourceRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    ResourcesComponent,
    ResourceListComponent,
    RemoveResourceDialogComponent,
    ResourceDetailComponent
  ],
  entryComponents: [
    RemoveResourceDialogComponent
  ],
  providers: [
    ResourceService
  ]
})

export class ResourcesModule {
}
