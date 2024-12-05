import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './global.scss';
import Join from './routes/Join';
import Login from './routes/Login';
import Layout from './routes/Layout';

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        {
          path: "",
          element: <Join />
        },
        {
          path: "breeds",
          element: <Login />
        },
      ]
    }
  ])
  return <RouterProvider router={router} />
}

export default App;
