import * as React from "react";
import { IStationSectionProps, IStationSectionState } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";

export default class StationSection extends React.Component<IStationSectionProps, IStationSectionState> {

    constructor(props: IStationSectionProps) {
        super(props);
        
        this.state = {
            abbr: props.match.params.abbr
        };
    } 

    public componentDidMount(): void {
        StationService.getStationsEstimates(this.state.abbr).then((data) => {
            console.log(data);
        });
    }

    public render (): JSX.Element {
        return (<div>test</div>);
    }


}