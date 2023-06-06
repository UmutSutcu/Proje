import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Main from "./pages/Main";
import ContentList from "./pages/ContentList";
function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path={'/'} element={<Main />}/>
          <Route path={'/contents'} element={<ContentList />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
