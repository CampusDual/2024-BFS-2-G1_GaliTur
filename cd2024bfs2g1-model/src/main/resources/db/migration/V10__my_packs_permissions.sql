UPDATE public.usr_role
SET rol_xml_client_permission='<?xml version="1.0" encoding="UTF-8"?><security></security>'::text, rol_json_client_permission='{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }]}', rol_notes='This is the client role'
WHERE rol_name='client';

UPDATE public.usr_role
SET rol_xml_client_permission='<?xml version="1.0" encoding="UTF-8"?><security></security>'::text, rol_json_client_permission='{ "menu": [{ "attr": "admin", "visible": false, "enabled": false },{ "attr": "mypacks", "visible": false, "enabled": false }]}', rol_notes='This is the guide role'
WHERE rol_name='guide';

UPDATE public.usr_role
SET rol_xml_client_permission='<?xml version="1.0" encoding="UTF-8"?><security></security>'::text, rol_json_client_permission='{ "menu": [{ "attr": "admin", "visible": false, "enabled": false },{ "attr": "mypacks", "visible": false, "enabled": false }]}', rol_notes='This is the manager role'
WHERE rol_name='manager';

UPDATE public.usr_role
SET rol_xml_client_permission='<?xml version="1.0" encoding="UTF-8"?><security></security>'::text, rol_json_client_permission='{ "menu": [{ "attr": "admin", "visible": false, "enabled": false },{ "attr": "mypacks", "visible": false, "enabled": false }]}', rol_notes='This is the user role'
WHERE rol_name='user';

UPDATE public.usr_role
SET rol_xml_client_permission='<?xml version="1.0" encoding="UTF-8"?><security></security>'::text, rol_json_client_permission='{ "menu": [{ "attr": "admin", "visible": false, "enabled": false },{ "attr": "mypacks", "visible": false, "enabled": false }]}', rol_notes='This is the merchant role'
WHERE rol_name='merchant';