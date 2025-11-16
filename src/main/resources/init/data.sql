INSERT INTO public.users(username, "password", "role")
VALUES('test', 'test', 'TEACHER'), ('test1', 'test1', 'STUDENT');

INSERT INTO public.categories("name")
VALUES('test_category');

INSERT INTO public.courses(title, description, category_id, teacher_id)
VALUES('test_course', 'This is a test course description.', 1, 1);

INSERT INTO public.modules(title, order_index, description, course_id)
VALUES('test_module', 1, 'This is a test module description', 1);

INSERT INTO public.lessons(title, content, video_url, module_id)
VALUES('test_lesson', 'This is a test lesson content.', 'https://demo.ru/demo', 1);

INSERT INTO public.enrollments(user_id, course_id, status)
VALUES(2, 1, 'COMPLETED');

INSERT INTO public.assignments(title, description, due_date, max_score, lesson_id)
VALUES('test_assignment', 'This is a test assignment description.', '2025-12-31',100, 1);

INSERT INTO public.submissions(content, score, feedback, student_id, assignment_id)
VALUES('Test content', 5, 'Test feedback', 2, 1);

INSERT INTO public.quizes(title, time_limit, module_id)
VALUES('test_quiz', 100, 1);