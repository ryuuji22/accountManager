INSERT INTO public.roles values ('8c37cde1-1146-4c6b-8e2b-b24a813dff33','ROLE_ADMIN');
INSERT INTO public.roles values ('60b40c14-84f5-4432-b614-8c4ad0f012f6','ROLE_USER');

INSERT INTO public.users values (
    'f85b529d-e3c9-4849-87d0-760c4edb3060',
    '2022-08-07',
    TRUE,
    '1715849731',
    '$2a$12$hMZpDsEUpPlNRBjEhZ6tCejkab/9K6985omuzrdeWa049AJLuKR.C'
    );

INSERT INTO public.users_roles values (
    'f85b529d-e3c9-4849-87d0-760c4edb3060',
    '8c37cde1-1146-4c6b-8e2b-b24a813dff33'
    );