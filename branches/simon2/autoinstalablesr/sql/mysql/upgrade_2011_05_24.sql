ALTER TABLE DOCUMENT ADD relevance INT NULL;
UPDATE DOCUMENT SET relevance = 0;
COMMIT;

Resource bundles nuevos