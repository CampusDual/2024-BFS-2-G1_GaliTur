import { MenuRootItem } from 'ontimize-web-ngx';
import { BusinessHomeComponent } from '../main/business/business-home/business-home.component';

export const MENU_CONFIG: MenuRootItem[] = [
  { id: 'home', name: 'HOME', icon: 'home', route: '/main/home' },
  { id: 'home-public', name: 'HOME', icon: 'home', route: '/home' },
  
  {  id: 'packssolo', name: 'PACKS', route: '/main/packs', icon: 'hiking'},
  { id: 'packs-public', name: 'PACKS', route: '/packs', icon: 'inventory_2'},
  { id: 'packmd', name: 'PACKS', icon: 'hiking',
    items: [
      { id: 'packs', name: 'PACKS', route: '/main/packs', icon: 'inventory_2'},
      { id: 'mypacks', name: 'MYPACKS', route: '/main/pack-client', icon: 'backpack' },
      { id: 'managepacks', name: 'MANAGEPACKS', route: '/main/pack-manage', icon: 'settings' },
      { id: 'packsnew', name: 'PACKS_NEW', route: '/main/packs/new', icon: 'add' }
    ]
    },
    {id: 'routessolo', name: 'ROUTES', icon: 'route', route: '/main/routes'},
    {id: 'routes-public', name: 'ROUTES', icon: 'route', route: '/routes'},
  { id: 'routesmd', name: 'ROUTES', icon: 'route',
    items: [
      {id: 'routes', name: 'ROUTES', icon: 'map', route: '/main/routes'},
      {id: 'routesnew', name: 'ROUTESNEW', icon: 'add', route: '/main/routes/new'},
    ]
  },
  {id: 'businessessolo', name: 'BUSINESSES', route: '/main/businesses', icon: 'business'},
  {id: 'businesses-public', name: 'BUSINESSES', route: '/businesses', icon: 'business'},
  { id: 'businessesmd', name: 'BUSINESSES', icon: 'business',
  items: [
    {id: 'businesses', name: 'BUSINESSES', route: '/main/businesses', icon: 'business'},
    {id: 'businessesnew', name: 'BUSINESSESNEW', route: '/main/businesses/new', icon: 'add'}
  ]
  },
{ id: 'graphics', name: 'GRAPHICS', route: '/main/graphics', icon: 'bar_chart'},
  {
    id: 'admin', name: 'ADMIN', icon: 'admin_panel_settings',
    items: [
      { id: 'roles', name: 'ROLES', route: '/main/admin/roles', icon: 'supervisor_account' },
      { id: 'users', name: 'USERS', route: '/main/admin/users', icon: 'person' }
    ]
  },
  { id: 'logout', name: 'LOGOUT', route: '/login', icon: 'power_settings_new', confirm: 'yes' }
];
