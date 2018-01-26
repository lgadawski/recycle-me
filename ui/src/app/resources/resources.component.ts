import { Component, OnInit } from '@angular/core';

import { Resource } from './resource';

import { ResourcesService } from './resources.service';


@Component({
  selector: 'resources-list',
  styleUrls: ['./resources.component.css'],
  templateUrl: './resources.component.html'
})
export class ResourcesComponent implements OnInit {

  resy: Resource[];

  constructor(private resourcesService: ResourcesService) {}

  ngOnInit() {
    this.getResources();
  }

  getResources(): void {
    this.resourcesService.get()
      .subscribe(resy => this.resy = resy);
  }

}
