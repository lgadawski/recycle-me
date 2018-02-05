import {InjectionToken} from '@angular/core';

import {IAppConfig} from './iapp.config';

export let APP_CONFIG = new InjectionToken('app.config');

export const AppConfig: IAppConfig = {
  routes: {
    resources: 'resources',
    error404: '404'
  },
  endpoints: {
    resources: 'api/resources'
  },
  votesLimit: 3,
  topResourcesLimit: 4,
  snackBarDuration: 3000
};
