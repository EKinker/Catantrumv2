import React, { useEffect, useState } from 'react';
import axios from 'axios';

const OwnedGames = () => {
    const [games, setGames] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios
        .get('http://localhost:8080/api/games/owned')
        .then((response) => {
            setGames(response.data);
            setLoading(false);
        })
        .catch((err) => {
            setError('Error fetching data');
            setLoading(false);
        });
    }, []);

    if (loading) {
    return <div> Loading... </div>;
    }

    if (error) {
        return <div> {error} </div>;
    }

    return (
        <div>
            <h1> Owned Games </h1>
            {games.length === 0 ? (
                <p> No games available. </p> )
                : (
                <ul>
                {games.map((game) => (
                    <li key={game.id}>
                        <strong>{game.name}</strong>
                        (Min Players: {game.minPlayers}, Max Players: {game.maxPlayers})
                        <br />
                        Min Age: {game.minAge}, Min Time: {game.minTime} min, Max Time: {game.maxTime} min
                        </li>
                        ))}
                        </ul>
                        )}
                        </div>
    );
};

export default OwnedGames