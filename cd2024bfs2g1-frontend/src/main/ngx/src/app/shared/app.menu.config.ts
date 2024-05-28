import { MenuRootItem } from 'ontimize-web-ngx';
import { BusinessHomeComponent } from '../main/business/business-home/business-home.component';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  {id: 'businessessolo', name: 'BUSINESSES', tooltip: 'BUSINESSES', route: '/main/businesses', icon: 'business'},
  { id: 'businessesmd', name: 'BUSINESSES', tooltip: 'BUSINESSES', icon: 'business',
  items: [
    {id: 'businesses', name: 'BUSINESSES', tooltip: 'BUSINESSES', route: '/main/businesses', icon: 'business'},
    {id: 'businesses-public', name: 'BUSINESSES', tooltip: 'BUSINESSES', route: '/main/businesses', icon: 'business'},
    {id: 'businessesnew', name: 'BUSINESSESNEW', tooltip: 'BUSINESSES', route: '/main/businesses/new', icon: 'add'}
  ]
  },
  {  id: 'packssolo', name: 'PACKS', tooltip: 'PACKS', route: '/main/packs', icon: 'hiking'},
  { id: 'packmd', name: 'PACKS', tooltip: 'PACKS', icon: 'hiking',
    items: [
      { id: 'packs', name: 'PACKS', tooltip: 'PACKS', route: '/main/packs', icon: 'inventory_2'},
      { id: 'packs-public', name: 'PACKS', tooltip: 'PACKS', route: '/packs', icon: 'inventory_2'},
      { id: 'mypacks', name: 'MYPACKS', tooltip: 'My Packs', route: '/main/pack-client', icon: 'backpack' },
      { id: 'packsnew', name: 'PACKS_NEW', tooltip: 'New Pack', route: '/main/packs/new', icon: 'add' }
    ]
    },
    {id: 'routessolo', name: 'ROUTES', icon: 'route', route: '/main/routes'},
  { id: 'routesmd', name: 'ROUTES', icon: 'route', 
    items: [
      {id: 'routes', name: 'ROUTES', icon: 'route', route: '/main/routes'},
      {id: 'routesnew', name: 'ROUTESNEW', icon: 'add', route: '/main/routes/new'},
      {id: 'routes-public', name: 'ROUTES', icon: 'route', route: '/main/routes'}
    ]
  },
  {
    id: 'admin', name: 'ADMIN', tooltip: 'ADMIN', icon: 'admin_panel_settings',
    items: [
      { id: 'roles', name: 'ROLES', tooltip: 'ROLES', route: '/main/admin/roles', icon: 'supervisor_account' },
      { id: 'users', name: 'USERS', tooltip: 'USERS', route: '/main/admin/users', icon: 'person' }
    ]
  },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
