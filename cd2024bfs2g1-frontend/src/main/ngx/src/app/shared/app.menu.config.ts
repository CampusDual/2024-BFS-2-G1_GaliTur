import { MenuRootItem } from 'ontimize-web-ngx';
import { BusinessHomeComponent } from '../main/business/business-home/business-home.component';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'businesses', name: 'BUSINESSES', tooltip: 'BUSINESSES', icon: 'business',
  items: [
    { id: 'businesses', name: 'Businesses', tooltip: 'Businesses', route: '/main/businesses', icon: 'supervisor_account' },
    { id: 'mybusinesses', name: 'My Businesses', tooltip: 'My Businesses', route: '/main/businesses/business-merchant', icon: 'insert_chart' }
  ]
  },
  { id: 'routes', name: 'ROUTES', icon: 'route', route: '/main/routes' },
  {
    id: 'admin', name: 'ADMIN', tooltip: 'ADMIN', icon: 'admin_panel_settings',
    items: [
      { id: 'roles', name: 'ROLES', tooltip: 'ROLES', route: '/main/admin/roles', icon: 'supervisor_account' },
      { id: 'users', name: 'USERS', tooltip: 'USERS', route: '/main/admin/users', icon: 'person' }
    ]
  },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
