ALTER TABLE SIMON.VERSION ADD commentsEnabled INT NULL;
UPDATE SIMON.VERSION
SET commentsEnabled = 1;

ALTER TABLE SIMON.SYSTEMUSER ADD canProposeParagraph INT NULL;
UPDATE SIMON.SYSTEMUSER
SET canProposeParagraph = 1
WHERE delegate = 1;

UPDATE SIMON.SYSTEMUSER
SET canProposeParagraph = 0
WHERE delegate = 0;
COMMIT;