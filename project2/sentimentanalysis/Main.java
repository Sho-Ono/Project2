package sentimentanalysis;

import java.util.Set;

/**
 * Testing routine for sentiment analysis.
 * 
 * @author Archer Murray
 */
public class Main
{
	public static void main(String[] args)
	{
		// Testing Tweet class methods
		System.out.println("Testing Tweet class:");
		Tweet testTweet = new Tweet(4, 3, "tprya",
				"@stellargirl I loooooooovvvvvveee my Kindle2. Not that the DX is cool; but the 2 is fantastic in its own right.");
		System.out.println(testTweet.getId());
		System.out.println(testTweet.getPolarity());
		System.out.println(testTweet.getUser());
		System.out.println(testTweet.getText());
		System.out.println(testTweet);
		System.out.println("----------");
		// Testing TweetCollection class methods
		System.out.println("Testing TweetCollection class:");
		// Constructor, readFile(String)
		TweetCollection tc = new TweetCollection(
				"./sentimentanalysis/trainingProcessed.txt", 1600000);
		System.out.println(tc);
		System.out.println("----------");
		// rewriteFile(String), writeFile(String)
		tc.rewriteFile("./sentimentanalysis/trainingWritten.txt");
		TweetCollection testTC = new TweetCollection(
				"./sentimentanalysis/trainingWritten.txt", 1600000);
		System.out.println(testTC);
		System.out.println("----------");
		// getAllTweetIds()
		Set<Long> tweetIds = testTC.getAllTweetIds();
		int printed = 0;
		System.out.print(
				"There are " + tweetIds.size() + " ids; the first 10 are: ");
		for (Long l: tweetIds) {
			System.out.print(l + " ");
			printed++;
			if (printed >= 10) {
				break;
			}
		}
		System.out.println();
		// size() / addTweet(Tweet)
		System.out.println(testTC.size());
		testTC.addTweet(testTweet);
		System.out.println(testTC.size());
		// getTweetById(long)
		System.out.println(testTC.getTweetById(3));
		// getTweetIdsByUser(String) / removeTweet(long)
		Set<Long> mattycuIds = testTC.getTweetIdsByUser("mattycu");
		System.out.println(mattycuIds.size()); // Should return 8
		for (Long l: mattycuIds) {
			System.out.println(testTC.removeTweet(l));
		}
		System.out.println(testTC.size());
		// predict(Tweet)
		System.out.println(testTC.predict(testTweet));
		// collectionTest(TweetCollection)
		TweetCollection testData = new TweetCollection(
				"./sentimentanalysis/testProcessed.txt", 500);
		System.out.println(testTC.collectionTest(testData));
		// rewriteFile()
		testTC.rewriteFile();
//		TweetCollection testTC2 = new TweetCollection(
//				"./sentimentanalysis/trainingWritten.txt", 1600000);
//		System.out.println(testTC2.size());
	}
}