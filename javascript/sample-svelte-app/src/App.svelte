<script>
	export let name;
	function handleClick(){
		console.log('Clicked');
		window.rudderanalytics.track('RS and Svelte track call initiated');
	}
</script>

<main>
	<h1>Hello {name}!</h1>
	<p>Click the button below to initiate a track call.</p>
	<!-- svelte-ignore missing-declaration -->
	<button on:click={handleClick}>RS and Svelte</button>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>