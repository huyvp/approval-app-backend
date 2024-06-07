ALTER TABLE "template"
    ADD FOREIGN KEY (create_user_id) REFERENCES users (id);

ALTER TABLE "template_form_builder"
   ADD FOREIGN KEY (template_id) REFERENCES "template" (id);

ALTER TABLE "approval_template"
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE "approval_template"
    ADD FOREIGN KEY (template_id) REFERENCES "template" (id);

ALTER TABLE "request"
    ADD FOREIGN KEY (create_user_id) REFERENCES users (id);
ALTER TABLE "request"
    ADD FOREIGN KEY (resource_id) REFERENCES "template" (id);

ALTER TABLE "request_form_value"
    ADD FOREIGN KEY (template_id) REFERENCES "template" (id);
ALTER TABLE "request_form_value"
    ADD FOREIGN KEY (request_id) REFERENCES "request" (id);

ALTER TABLE "request_approval"
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE "request_approval"
    ADD FOREIGN KEY (request_id) REFERENCES "request" (id);