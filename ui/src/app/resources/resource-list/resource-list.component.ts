import { Component, ViewChild } from '@angular/core';
import { Resource } from '../shared/resource.model';
import { ResourceService } from '../shared/resource.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { AppConfig } from '../../config/app.config';
import { Router } from '@angular/router';
import { LoggerService } from '../../core/logger.service';

@Component({
  selector: 'app-remove-resource-dialog',
  templateUrl: './remove-resource.dialog.html',
})

export class RemoveResourceDialogComponent {
  constructor() {
  }
}

@Component({
  selector: 'app-resource-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.scss']
})

export class ResourceListComponent {
  resources: Resource[];
  newResourceForm: FormGroup;
  error: string;
  @ViewChild('form') myNgForm; // just to call resetForm method
  constructor(private resourceService: ResourceService,
    private dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder) {

    this.newResourceForm = this.formBuilder.group({
      'name': ['', [Validators.required]],
      'symbol': ['', [Validators.required]]
    });

    this.resourceService.getAllResources().subscribe((resources: Array<Resource>) => {
      this.resources = resources.sort((a, b) => {
        return b.weight - a.weight;
      });
    });
  }

  createNewResource(newResource: Resource) {
    this.resourceService.createResource(newResource).subscribe((newResourceWithId) => {
      this.resources.push(newResourceWithId);
      this.myNgForm.resetForm();
    }, (response: Response) => {
      if (response.status === 500) {
        this.error = 'errorHasOcurred';
      }
    });
  }

  seeResourceDetails(resource): void {
    this.router.navigate([AppConfig.routes.resources + '/' + resource.id]);
  }

  remove(resourceToRemove: Resource): void {
    const dialogRef = this.dialog.open(RemoveResourceDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.resourceService.deleteResourceById(resourceToRemove.id).subscribe(() => {
          this.resourceService.showSnackBar('resourceRemoved');
          this.resources = this.resources.filter(resource => resource.id !== resourceToRemove.id);
        }, (response: Response) => {
          if (response.status === 500) {
            this.error = 'resourceDefault';
          }
        });
      }
    });
  }
}
