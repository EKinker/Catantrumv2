import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from './LoadingMessage';
import ErrorMessage from './ErrorMessage';
import GameList from './GamesList';
import '../css/OwnedGames.css';

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
        return <Loading />;
    }

    if (error) {
        return <ErrorMessage message={error} />;
    }

    return (
        <div className="owned-games">
            <h1>Owned Games</h1>
            {games.length === 0 ? (
                <p>No games available.</p>
            ) : (
                <GameList games={games} />
            )}
        </div>
    );
};

export default OwnedGames;
