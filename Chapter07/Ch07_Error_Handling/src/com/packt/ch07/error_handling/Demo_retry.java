package com.packt.ch07.error_handling;

import java.io.IOException;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class Demo_retry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Observable<Integer> observable = Observable.just(12, 30, 5, 50, 89);
		observable.filter(item -> {
			try {
				return check(item);
			} catch (IOException e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}).retry(3).subscribe(new Observer<Integer>() {

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.println("sequence completed");

			}

			@Override
			public void onError(Throwable throwable) {
				// TODO Auto-generated method stub
				throwable.printStackTrace();
			}

			@Override
			public void onNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("got:-" + value);

			}

			@Override
			public void onSubscribe(Disposable disposable) {
				// TODO Auto-generated method stub

			}
		});


	}

	public static boolean check(int item) throws IOException {
		boolean result = false;
		if (item > 10)
			result = true;
		else
			throw new IOException("***got an exception****");
		return result;

	}

}
