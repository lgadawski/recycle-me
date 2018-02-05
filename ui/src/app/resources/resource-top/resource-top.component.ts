import {Component} from '@angular/core';

import {Resource} from '../shared/resource.model';

import {ResourceService} from '../shared/resource.service';
import {AppConfig} from '../../config/app.config';

@Component({
  selector: 'app-resource-top',
  templateUrl: './resource-top.component.html',
  styleUrls: ['./resource-top.component.scss']
})
export class ResourceTopComponent {

  resources: Resource[] = null;

  constructor(private resourceService: ResourceService) {

    this.resourceService.getAllResources().subscribe((resources) => {
      this.resources = resources.sort((a, b) => {
        return b.weight - a.weight;
      }).slice(0, AppConfig.topResourcesLimit);
    });
  }

}
