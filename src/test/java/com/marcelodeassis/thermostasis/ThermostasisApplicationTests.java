package com.marcelodeassis.thermostasis;


import com.marcelodeassis.thermostasis.dto.Playlist;
import com.marcelodeassis.thermostasis.service.PlaylistMood;
import com.marcelodeassis.thermostasis.service.PlaylistService;
import com.marcelodeassis.thermostasis.service.restclient.PlaylistRecommendationClient;
import com.marcelodeassis.thermostasis.service.restclient.WeatherClient;
import com.marcelodeassis.thermostasis.service.restclient.payload.PlaylistRecommendation;
import com.marcelodeassis.thermostasis.service.restclient.payload.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ThermostasisApplicationTests {

	@Autowired
	PlaylistService playlistService;

	@Autowired
	WeatherClient openWeatherClient;

	@Autowired
	PlaylistRecommendationClient playlistClient;

	@Test
	void playlist_service() throws Exception {
		Playlist pl = playlistService.getPlaylist("Rio");
		Assert.isTrue(pl.getTracks().size() >= 1, "Playlist must have more than one element");
	}

	@Test
	void weather_client() throws Exception {
		Weather w = openWeatherClient.getWeather("Rio de Janeiro, RJ, Brazil");

		Assert.hasText(w.getCity(), "Rio de Janeiro");
		Assert.hasText(w.getCountry(), "BR");
		Assert.isTrue(w.getCelsiusTemp() >= 8, "IMPOSSIBLE! Rio would never be so cold!");
	}

	@Test
	void playlist_recommendation_client() throws Exception {
		PlaylistRecommendation pr = playlistClient.getRecommendation(PlaylistMood.ROCK);

		Assert.isTrue(pr.getTracks().size() >= 1, "Playlist must have more than one element");
		Assert.isTrue(pr.getTracks().get(0).getName().length() > 0, "Track name shouldn't be empty!");
		Assert.isTrue(pr.getTracks().get(0).getArtistsString().length() > 0, "Artist(s) name(s) shouldn't be empty!");
	}

	@Test
	void playlist_mood() throws Exception {
		Assert.isTrue(PlaylistMood.getPlaylistMood(31) == PlaylistMood.PARTY, "Above 30º = Party");
		Assert.isTrue(PlaylistMood.getPlaylistMood(30) == PlaylistMood.POP, "15º~30º = Pop");
		Assert.isTrue(PlaylistMood.getPlaylistMood(15) == PlaylistMood.POP, "15º~30º = Pop");
		Assert.isTrue(PlaylistMood.getPlaylistMood(14) == PlaylistMood.ROCK, "10º~14º = Rock");
		Assert.isTrue(PlaylistMood.getPlaylistMood(10) == PlaylistMood.ROCK, "10º~14º = Rock");
		Assert.isTrue(PlaylistMood.getPlaylistMood(9) == PlaylistMood.CLASSICAL, "Below 9º = Classical");
		Assert.isTrue(PlaylistMood.getPlaylistMood(0) == PlaylistMood.CLASSICAL, "Below 9º = Classical");
		Assert.isTrue(PlaylistMood.getPlaylistMood(-30) == PlaylistMood.CLASSICAL, "Below 9º = Classical");
	}

}
