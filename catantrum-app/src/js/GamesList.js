import React from 'react';

const GameList = ({ games }) => {
    return (
        <ul className="game-list">
            {games.map((game) => (
                <li key={game.id} className="game-item">
                    <strong>{game.name}</strong>
                    <p>
                        Min Players: {game.minPlayers}, Max Players: {game.maxPlayers}
                    </p>
                    <p>
                        Min Age: {game.minAge}, Min Time: {game.minTime} min, Max Time: {game.maxTime} min
                    </p>
                </li>
            ))}
        </ul>
    );
};

export default GameList;
