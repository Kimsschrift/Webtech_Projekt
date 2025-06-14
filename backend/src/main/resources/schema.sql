-- Extend allowed values for app_user.role
ALTER TABLE IF EXISTS app_user
DROP CONSTRAINT IF EXISTS app_user_role_check;
ALTER TABLE IF EXISTS app_user
    ADD CONSTRAINT app_user_role_check CHECK (role IN ('COMPANY', 'APPLICANT', 'ADMIN'));