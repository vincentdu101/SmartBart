import * as React from "react";
import * as ConfigService from "../../services/Config/ConfigService";
import "./PlannerTable.scss";

import { Table } from "reactstrap";

interface IState {
    plans: any;
}

class PlannerTable extends React.Component<{}, IState> {

    constructor(props: any) {
        super(props);

        this.outputRequestScheduleRow = this.outputRequestScheduleRow.bind(this);

        this.state = {
            plans: {}
        };
    }

    public componentDidMount() {
        this.setState({plans: {}});
        fetch(ConfigService.staticFilteredEstimates + "?orig=24th&dest=rock").then(results => {
            return results.json();
        }).then(data => {
            console.log(data);
            this.setState({plans: data});
        });
    }

    private outputRequestScheduleRow(rows): any {
        return rows.map((row) => {
            return (
                <tr>
                    <td>{row.destDateTime}</td>
                    <td>{row.tripTime}</td>
                </tr>
            )
        });
    }

    public render() {
        return (
            <section className="planner-section">
                <div className="card">
                    <div className="card-body">
                        <div>Origin: {this.state.plans.origin}</div>
                        <div>Destination: {this.state.plans.destination}</div>
                        <div>Schedule Num: {this.state.plans.schedNum}</div>
                        <div>Request Time: {this.state.plans.schedule.datetime}</div>
                    </div>
                </div>

                <Table className="table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Origin</th>
                            <th scope="col">Destination</th>
                            <th scope="col">Origin</th>
                            <th scope="col">Origin</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{this.state.plans.origin}</td>
                            <td>{this.state.plans.destination}</td>
                        </tr>
                    </tbody>
                </Table>
            </section>
        );
    }

}

export default PlannerTable;