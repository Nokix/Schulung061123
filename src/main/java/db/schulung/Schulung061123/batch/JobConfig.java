package db.schulung.Schulung061123.batch;

import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.generator.Generator;
import db.schulung.Schulung061123.repo.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class JobConfig {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    PlatformTransactionManager platformTransactionManager;

    //    @Bean
    Job firstJob() {
        Tasklet tasklet = (contribution, chunkContext) -> {
            Object startAt = chunkContext.getStepContext().getJobParameters().get("startAt");
            System.out.println("Job started at " + startAt);
            System.out.println("Hallo Welt");
            return RepeatStatus.FINISHED;
        };

        Step step = new StepBuilder("first Step", jobRepository)
                .tasklet(tasklet, platformTransactionManager).build();

        return new JobBuilder("First Job", jobRepository)
                .start(step).build();
    }

    @Bean
    Job bigJob(
            Step generatingStep,
            Step toCsvStep) {
        return new JobBuilder("Big Job", jobRepository)
                .start(generatingStep)
                .next(toCsvStep)
                .build();
    }

    @Bean("toCsvStep")
    Step fromDbtoCsvStep(
            @Qualifier("Student_from_db") ItemReader<Student> reader,
            @Qualifier("Check_Student_name") ItemProcessor<Student, Student> processor,
            @Qualifier("Student_to_csv") ItemWriter<Student> writer
    ) {
        return new StepBuilder("read from Db -> write to Csv", jobRepository)
                .<Student, Student>chunk(10, platformTransactionManager)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(Integer.MAX_VALUE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("Check_Student_name")
    ItemProcessor<Student, Student> nameLengthChecker() {
        return student -> {
            if (student.getName().length() < 14) {
                System.out.println(student.getName() + " is too short!!!!!!!");
                throw new Exception();
            }
            return student;
        };
    }

    @Bean
    @Qualifier("Student_from_db")
    ItemReader<Student> readFromDb(
            StudentRepository studentRepository,
            @Value("findAll") String method
    ) {
        return new RepositoryItemReader<>() {{
            setRepository(studentRepository);
            setMethodName(method);
            setSort(Map.of("id", Sort.Direction.ASC));
            setPageSize(5);
        }};
    }

    @Bean
    @Qualifier("Student_to_csv")
    ItemWriter<Student> toCsvWriter(
            WritableResource resource,
            LineAggregator<Student> aggregator
    ) {
        return new FlatFileItemWriter<>() {{
            setResource(resource);
            setAppendAllowed(false);
            setLineAggregator(aggregator);
        }};
    }

    @Bean
    LineAggregator<Student> studentLineAggregator() {
        return new DelimitedLineAggregator<>(){{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<>(){{
                setNames(new String[]{"id", "name"});
            }});
        }};
    }

    @Bean
    WritableResource resource(@Value("${csv.output}") String path) {
        return new FileSystemResource(path);
    }

    @Bean("generatingStep")
    Step generateStudentsInDatabaseStep(
            @Qualifier("readStrings") ItemReader<String> reader,
            @Qualifier("createStudentsByName") ItemProcessor<String, Student> processor,
            @Qualifier("Student_to_db") ItemWriter<Student> writer

    ) {

        return new StepBuilder("generate Studs to Database", jobRepository)
                .<String, Student>chunk(5, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    ItemWriter<Student> soutStudent() {
        return chunk -> {
            chunk.forEach(System.out::println);
            System.out.println("#############");
        };
    }

    @Bean
    @Qualifier("Student_to_db")
    ItemWriter<Student> toDatabase(StudentRepository studentRepository) {
        return new RepositoryItemWriter<>() {{
            setRepository(studentRepository);
        }};
    }

    @Bean
    ItemReader<String> readStrings(
            Iterator<String> list
    ) {
        return new IteratorItemReader<>(list);
    }

    @Bean
    Iterator<String> getRandomNames(
            Generator generator,
            @Value("40") int amount) {
        return Stream.generate(generator::name).limit(amount).iterator();
    }

    @Bean
    ItemProcessor<String, Student> createStudentsByName() {
        return Student::new;
    }
}
