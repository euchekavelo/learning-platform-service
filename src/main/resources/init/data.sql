INSERT INTO public.users(username, "password", "role")
VALUES('test', 'test', 'TEACHER'), ('test1', 'test1', 'STUDENT');

INSERT INTO public.profiles(user_id, avatar_url, bio)
VALUES(1, 'https://example.com/avatar', 'Test bio');

INSERT INTO public.categories("name")
VALUES('test_category');

INSERT INTO public.courses(title, description, category_id, teacher_id, start_date)
VALUES('test_course', 'This is a test course description.', 1, 1, '2025-12-29');

INSERT INTO public.modules(title, order_index, description, course_id)
VALUES('test_module', 1, 'This is a test module description', 1);

INSERT INTO public.lessons(title, content, video_url, module_id)
VALUES('test_lesson', 'This is a test lesson content.', 'https://demo.ru/demo', 1);

INSERT INTO public.enrollments(user_id, course_id, status, enroll_date)
VALUES(2, 1, 'COMPLETED', NOW());

INSERT INTO public.assignments(title, description, due_date, max_score, lesson_id)
VALUES('test_assignment', 'This is a test assignment description.', '2025-12-31',100, 1);

INSERT INTO public.submissions(content, score, feedback, student_id, assignment_id, submitted_at)
VALUES('Test content', 5, 'Test feedback', 2, 1, NOW());

INSERT INTO public.quizes(title, time_limit, module_id)
VALUES('test_quiz', 100, 1);

INSERT INTO public.questions(quiz_id, "text", "type")
VALUES(1, 'This is a test text', 'SINGLE_CHOICE');

INSERT INTO public.answer_options(is_correct, question_id, "text")
VALUES(true, 1, 'This is a test text');

INSERT INTO public.quiz_submissions(quiz_id, score, student_id, taken_at)
VALUES(1, 100, 2, NOW());

INSERT INTO public.tags("name")
VALUES('Test name for tag');

INSERT INTO public.course_tags(course_id, tag_id)
VALUES(1, 1);

INSERT INTO public.course_reviews(course_id, rating, student_id, created_at, "comment")
VALUES(1, 100, 1, NOW(), 'This course is good.');