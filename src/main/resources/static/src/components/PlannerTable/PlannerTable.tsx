import * as React from "react";
import { ConfigService } from "../../services/Config/ConfigService";
import "./PlannerTable.scss";
import { IPlannerProps, IPlannerRequest, ISchedule, IPlannerState } from "../../types/PlannerTypes";
import { Table } from "reactstrap";
import { DateService } from "../../services/DateService/DateService";
 
export default class PlannerTable extends React.Component<IPlannerProps, IPlannerState> {

    constructor(props: any) {
        super(props);

        this.outputRequestScheduleRow = this.outputRequestScheduleRow.bind(this);
        this.updateTableAndState = this.updateTableAndState.bind(this);

        this.state = {
            plans: {}
        };
    }

    private updateTableAndState(props: Readonly<any>): void {
        if (props.origin && props.destination) {
            let params = "?orig=" + props.origin + "&dest=" + props.destination; 
            fetch(ConfigService.staticFilteredEstimates + params).then(results => {
                return results.json();
            }).then(data => {
                console.log(data);
                this.setState({plans: data});
            });
        }
    }

    public componentDidMount(): void {
        this.setState({plans: {}});
    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        console.log("nextProps ", nextProps);
        this.updateTableAndState(nextProps);
    }

    private outputRequestScheduleRow(schedule: ISchedule): JSX.Element[] {
        let rows: IPlannerRequest[] = schedule ? schedule.request : [];

        return rows.map((row: IPlannerRequest, index: number) => {
            let origDateTime = DateService.printFormattedTime(row.origDateTime);
            let destDateTime = DateService.printFormattedTime(row.destDateTime);
            console.log(row);
            return (
                <tr key={row.origin + index}>
                    <td key={row.origin}>{row.origin}</td>
                    <td key={row.destination}>{row.destination}</td>
                    <td key={row.fare}>{row.fare}</td>
                    <td key={row.origDateTime}>{origDateTime}</td>
                    <td key={row.destDateTime}>{destDateTime}</td>
                    <td key={row.clipper}>{row.clipper}</td>
                    <td key={row.tripTime}>{row.tripTime}</td>
                    <td key={row.co2}>{row.co2}</td>
                </tr>
            );
        });
    }

    private printRequestDateTime(schedule: ISchedule): string {
        let date = this.state.plans.schedule ? this.state.plans.schedule.dateTime : "";

        if (date !== "") {
            let dateObj = new Date(date);
            return dateObj.toLocaleDateString() + " " + dateObj.toLocaleTimeString();
        }

        return date;
    }

    public render(): JSX.Element {
        return (
            <section className="planner-table">
                <div className="card">
                    <div className="card-body">
                        <div>Origin: {this.state.plans.origin}</div>
                        <div>Destination: {this.state.plans.destination}</div>
                        <div>Schedule Num: {this.state.plans.schedNum}</div>
                        <div>Schedule: {this.printRequestDateTime(this.state.plans.schedule)}</div>
                    </div>
                </div>
                <Table className="table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Origin</th>
                            <th scope="col">Destination</th>
                            <th scope="col">Fare</th>
                            <th scope="col">Origin Date and Time</th>
                            <th scope="col">Destination Date and Time</th>
                            <th scope="col">Clipper</th>
                            <th scope="col">Total Trip Time</th>
                            <th scope="col">co2 Emissions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.outputRequestScheduleRow(this.state.plans.schedule)}
                    </tbody>
                </Table>
            </section>
        );
    }

}
