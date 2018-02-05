import {Component} from '@angular/core';
import {Resource} from '../shared/resource.model';
import {ResourceService} from '../shared/resource.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-resource-detail',
  templateUrl: './resource-detail.component.html',
  styleUrls: ['./resource-detail.component.scss']
})

export class ResourceDetailComponent {
  resource: Resource;

  constructor(private resourceService: ResourceService,
              private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe((params: any) => {
      if (params['id']) {
        this.resourceService.getResourceById(params['id']).subscribe((resource: Resource) => {
          this.resource = resource;
        });
      }
    });
  }

}
