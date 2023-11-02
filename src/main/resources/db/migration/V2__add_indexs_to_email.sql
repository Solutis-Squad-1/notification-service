-- Index for table emails
CREATE INDEX idx_emails_deleted ON emails (deleted);
CREATE INDEX idx_emails_owner ON emails (owner);
CREATE INDEX idx_emails_email_from ON emails (email_from);
CREATE INDEX idx_emails_email_to ON emails (email_to);
CREATE INDEX idx_emails_status ON emails (status);