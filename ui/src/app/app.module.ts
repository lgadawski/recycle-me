import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ResourcesComponent } from './resources/resources.component';
import { ResourcesFormComponent } from './resources/add/resources-form.component';
import { AppService } from './app.service';
import { ResourcesService } from './resources/resources.service';
import { HttpClientModule } from '@angular/common/http';
import { RouteExampleComponent } from './route-example/route-example.component';

const routes: Routes = [
  {
    path: 'scala',
    component: RouteExampleComponent,
    data: { technology: 'Scala' }
  },
  {
    path: 'play',
    component: RouteExampleComponent,
    data: { technology: 'Play' }
  },
  {
    path: 'angular',
    component: RouteExampleComponent,
    data: { technology: 'Angular' }
  },
  { path: '', redirectTo: '/play', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    ResourcesComponent,
    ResourcesFormComponent,
    RouteExampleComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes, { useHash: true })
  ],
  providers: [AppService, ResourcesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
