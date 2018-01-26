import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from "@angular/forms";

import { Resource } from '../resource';
import { ResourcesService } from '../resources.service';

@Component({
  selector: 'resources-form',
  templateUrl: './resources-form.component.html'
  // styleUrls: ['./resources-form.component.css']
})
export class ResourcesFormComponent implements OnInit {

  constructor (
    private resourcesService: ResourcesService,
    private formBuilder: FormBuilder) {
      // empty
  }

  model = new Resource(null, '', '');
  submitted = false;
  errors: string[];
  resourcesForm: FormGroup;

  ngOnInit() {
    this.resourcesForm = this.formBuilder.group({
      'name':  [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(120)])],
      'symbol': [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(3)])]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.resourcesService.post(this.model);
  }

}
