package com.lms.changemaker.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.lms.changemaker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.QuizRepository;
import com.lms.changemaker.service.QuizService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuizServiceImpl implements QuizService {

	

@Autowired
private QuizRepository quizRepository;

@Override
@Transactional
public Quiz addQuiz(Quiz quiz) {
	return quizRepository.save(quiz);
}

@Override
public List<Quiz> findAllQuizs() {
	List<Quiz> quizList= quizRepository.findAll();

	quizList.stream().forEach(q->{
		q.quizOptions.stream().forEach(qQ->{
			if(qQ.getQuizOptionId()==q.getAnswer()) {
				q.setAns(qQ.getQuizChoice());
			}
		});
	});
	return quizList;
}

@Override
public Optional<Quiz> findQuizById(int quizId) {
	return quizRepository.findById(quizId);
}

	@Override
	public int updateQuizAnswer(int answer, int quizId) {
		return quizRepository.updateQuizAnswer(answer, quizId);
	}



	@Override
	@Transactional
	public void addNewQuiz(Quiz quiz) {
	  Quiz quiznew= addQuiz(quiz);


	 
		List<QuizOption> quizOptions = new ArrayList<>();

		quizOptions.add(new QuizOption(quiz.getOption1(),quiznew.getQuizId()));
		quizOptions.add(new QuizOption(quiz.getOption2(),quiznew.getQuizId()));
		quizOptions.add(new QuizOption(quiz.getOption3(),quiznew.getQuizId()));
		if(!quiz.getOption4().isEmpty())
		quizOptions.add(new QuizOption(quiz.getOption4(),quiznew.getQuizId()));
		
		Quiz  quiz1=new Quiz();
		quiz1.setQuizId(quiznew.getQuizId());
		quiz1.setQuizOptions(quizOptions);
		quiz1.setQuestion(quiznew.getQuestion());
		quiznew=addQuiz(quiz1);
		AtomicInteger answer= new AtomicInteger();

		String choice="";
		
			for (int i = 1; i <quizOptions.size()+1; i++) {
			if(Integer.parseInt(quiz.getAnswerNumber())==i){
				 choice=quizOptions.get(i-1).getQuizChoice();
				 break;
			}
		      }
		String finalChoice = choice;
		quiznew.getQuizOptions().forEach(t->{
			 if(t.getQuizChoice().equals(finalChoice)){
				 answer.set(t.getQuizOptionId());
			 }
		});

		updateQuizAnswer(answer.get(),quiznew.getQuizId());
		
	}

	
	@Override
	@Transactional
	public void editNewQuiz(Quiz quiz) {



		Quiz quiznew=findQuizById(quiz.getQuizId()).get();

		List<QuizOption> quizOptions= quiznew.getQuizOptions();


		       
		for (int i = 0; i <quizOptions.size() ; i++) {
			if(i==0&&!quizOptions.get(i).getQuizChoice().equals(quiz.getOption1())){
				quizOptions.get(i).setQuizChoice(quiz.getOption1());
			} else if(i==1&&!quizOptions.get(i).getQuizChoice().equals(quiz.getOption2())){
				quizOptions.get(i).setQuizChoice(quiz.getOption2());
			}
			else if(i==2&&!quizOptions.get(i).getQuizChoice().equals(quiz.getOption3())){
				quizOptions.get(i).setQuizChoice(quiz.getOption3());
			}
			else if(i==3&&!quizOptions.get(i).getQuizChoice().equals(quiz.getOption4())){
				quizOptions.get(i).setQuizChoice(quiz.getOption4());
			}
		}




		quiznew.setQuestion(quiznew.getQuestion());
		AtomicInteger answer= new AtomicInteger();

		String choice="";

		for (int i = 1; i <quizOptions.size()+1; i++) {
			if(Integer.parseInt(quiz.getAnswerNumber())==i){
				choice=quizOptions.get(i-1).getQuizChoice();
				break;
			}
		}
		String finalChoice = choice;
		quiznew.getQuizOptions().forEach(t->{
			if(t.getQuizChoice().equals(finalChoice)){
				answer.set(t.getQuizOptionId());
			}
		});
		addQuiz(quiznew);
		updateQuizAnswer(answer.get(),quiznew.getQuizId());

	}

}
